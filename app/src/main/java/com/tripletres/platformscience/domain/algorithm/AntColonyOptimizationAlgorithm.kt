package com.tripletres.platformscience.domain.algorithm

import com.tripletres.platformscience.domain.ShipmentDriverAssignation.SuitabilityScore
import com.tripletres.platformscience.domain.model.Driver

/**
 * Ant colony optimization algorithm: AI meta heuristic for assignation problem:
 *
 * Algorithm:
 * 1. Repeat until stop criterion satisfied, do:
 * 1.1  Generate ant set
 * 1.2  Position ants in initial position
 * 1.3  For each ant, do:
 * 1.3.1    Generate ant path (solution)
 * 1.3.2    Evaluate fitness
 * 1.4.  Apply pheromone
 * 1.5.  Update best solution
 * 2. Return best solution
 *
 */
class AntColonyOptimizationAlgorithm : IAssignationAlgorithm {
    private val MAX_ITERS = 500
    private val MAX_ANTS = 20
    private val EXPLORE_RATIO = 2
    private val PHEROMONE_INC = 1f
    private lateinit var initialInput: List<SuitabilityScore>
    private var driverCount: Int = 0
    private var shipmentCount: Int = 0
    private val pheromonePath = mutableMapOf<SuitabilityScore, Float>()

    override fun getBestMatching(input: MutableList<MutableList<SuitabilityScore>>): List<Driver> {
        initialInput = prepareInitialInput(input)
        return execute()
    }

    /**
     * Prepare initial input, creates a pheromone path with initial values and size of drivers and
     * shipment with given input
     * @param input a non empty input matrix nxm with [SuitabilityScore] values
     */
    private fun prepareInitialInput(input: MutableList<MutableList<SuitabilityScore>>): List<SuitabilityScore> {
        val res = mutableListOf<SuitabilityScore>()
        for (i in 0 until input.size) {
            for (j in 0 until input[i].size) {
                res.add(input[i][j])
                pheromonePath[input[i][j]] = 0f
            }
        }
        shipmentCount = input.size
        driverCount = input[0].size
        return res.toList()
    }

    /**
     * Main execution program
     *
     * @return best driver list solution
     */
    private fun execute(): List<Driver> {
        var iteration = 0
        val bestSolution = mutableListOf<Ant>()
        //Repeat until solution or max iteration is acceptable
        while (stopCondition(iteration++)) {
            val ants = MutableList(MAX_ANTS) { Ant() }
            var bestAnt = ants[0]
            ants.forEach { ant ->
                //Generate a path with assignation
                applyAntAssignationPath(ant)

                //Update best ant path
                if (ant.ss < bestAnt.ss) {
                    bestAnt = ant
                }
            }

            //Update pheromone path (globally) where ant traveled
            updatePheromonePhat(bestAnt)

            //Save the best ant for iteration
            bestSolution.add(bestAnt)
        }

        //Sort by ss for best solution
        bestSolution.sortBy { it.ss }
        return buildDriverList(bestSolution.first())
    }


    /**
     * Defines a stop condition strategy
     */
    private fun stopCondition(iteration: Int = 0): Boolean {
        return iteration < MAX_ITERS
    }

    /**
     * Given an ant builds a path with a valid assignation
     * @param ant
     */
    private fun applyAntAssignationPath(ant: Ant) {
        var allPaths: List<SuitabilityScore> = initialInput
        var firstRun = true
        //Assign drivers to ant path
        do {
            //And move ant to next position
            val lastPosition = if (firstRun) {
                firstRun = !firstRun
                //Initial ant position could be random
                initialInput.random().also { //Eg: Driver 3
                    ant.ss += it.ss
                }
            } else {
                //Use pheromone path to update new ant position
                moveAntToNextPosition(ant, allPaths)
            }
            //Add record of last position to ant path
            ant.path.add(lastPosition)

            //Remove driver from all paths assignation
            allPaths = allPaths.filter {
                it.driver.id != lastPosition.driver.id &&
                        it.shipment.id != lastPosition.shipment.id
            }
        } while (allPaths.isNotEmpty())
    }

    /**
     * Strategy to move an ant to next promising position, assuming any ant can be an scout
     * according to [EXPLORE_RATIO] value
     */
    private fun moveAntToNextPosition(
        ant: Ant,
        allPaths: List<SuitabilityScore>,
    ): SuitabilityScore {
        //Initialize the first promising path to follow
        var promising: SuitabilityScore = allPaths[0]

        //For each path look for
        allPaths.forEach {
            if (it.ss < promising.ss) {
                //Check phat of pheromone
                if (pheromonePath.containsKey(it) && pheromonePath.containsKey(promising) &&
                    pheromonePath[it]!! > pheromonePath[promising]!!
                ) {
                    promising = it
                }
            }
        }

        //Ant strategy: Explore or follow pheromone
        if ((0 until 100).random() < EXPLORE_RATIO) { // ie: only 1%, 2% etc will explore another path
            // Explore another path
            promising = allPaths.random()
        }

        //update ss
        ant.ss += promising.ss
        return promising
    }

    /**
     * Increases pheromone path by [PHEROMONE_INC] value
     */
    private fun updatePheromonePhat(ant: Ant) {
        ant.path.forEach {
            pheromonePath[it] = pheromonePath[it]?.plus(PHEROMONE_INC) ?: PHEROMONE_INC
        }
    }

    /**
     * Get solution
     */
    private fun buildDriverList(ant: Ant): List<Driver> {
        return ant.path.map { it.driver.copy(shipment = it.shipment) }
    }

    /**
     * Ant with properties
     */
    internal data class Ant(
        val path: MutableList<SuitabilityScore> = mutableListOf(),
        var ss: Float = 0f,
    )

}
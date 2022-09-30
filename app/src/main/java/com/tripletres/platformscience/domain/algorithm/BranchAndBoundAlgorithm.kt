package com.tripletres.platformscience.domain.algorithm

import com.tripletres.platformscience.domain.ShipmentDriverAssignation.SuitabilityScore
import com.tripletres.platformscience.domain.algorithm.BranchAndBoundAlgorithm.Node
import com.tripletres.platformscience.domain.model.Driver
import com.tripletres.platformscience.domain.model.Shipment

/**
 * Branch and bound algorithm. Approximation to solve the Assignation problem
 *
 * Algorithm execution:
 * 1. From given matrix of suitability score (between drivers and shipments)
 * 2. Create a priority queue
 * 3. Create a root [Node] node dummy and add it to priority queue
 * 4. Loop until priority queue is empty OR Last Driver has been reached, do:
 * 4.1  Get lowest ss value node
 * 4.2  Loop for each driver, do:
 * 4.3      If unassigned node then
 * 4.4      Add to the queue -> unassigned nodes ([Node] child) with the path ss and calculate
 *          total ss (see getLowestSSAfterDriverAssignedShipment)  and the "path of ss".
 * 5. Create a list of drivers to return result
 *
 * Routine getLowestSSAfterDriverAssignedShipment:
 * 1. Create a queue of available shipments
 * 2. Loop for each driver (next one) do:
 * 2.1. Loop for each shipment do:
 * 2.1.1    Get the minimum ss when node is not assigned and it's available
 * 2.2  Accumulate the minimum ss and add to queue the minimum shipment
 * 3. Return the total minimum cost
 *
 * References:
 * https://www.geeksforgeeks.org/branch-and-bound-algorithm/
 * https://tutorialspoint.dev/algorithm/branch-and-bound-algorithm/branch-bound-set-4-job-assignment-problem
 */
class BranchAndBoundAlgorithm : IAssignationAlgorithm {

    private var n = 0

    override fun getBestMatching(input: MutableList<MutableList<SuitabilityScore>>): List<Driver> {
        n = input.size
        return execute(matrix = input)
    }

    private fun execute(matrix: MutableList<MutableList<SuitabilityScore>>): List<Driver> {
        //Priority queue for nodes that are "live"
        val priorityQueue = mutableListOf<Node>()

        // Initialize nodes with root of search (dummy)
        val assigned = HashMap<Shipment, Boolean>()
        val root = Node.dummy()
        var minOut = root
        priorityQueue.add(root)

        //Continue until priority queue has nodes
        while (priorityQueue.isNotEmpty()) {
            //Find a node with lowest ss
            val min = priorityQueue.minByOrNull { it.ss } ?: priorityQueue[0]
            minOut = min

            //And remove it from the queue
            priorityQueue.remove(min)

            //Get next driver index
            val i = min.driverIndex + 1 // Start point = 0

            //Stop condition: if last driver is reached
            if (i == n) {
                return getBuildNodes(min)
            } else {
                //For each shipment find unassigned shipments
                matrix[i].forEachIndexed { j, it ->
                    //If unassigned
                    if (!min.assigned.containsKey(it.shipment)) {
                        val child = Node.newNode(
                            driver = matrix[i][j].driver,
                            driverIndex = i, // should have here?
                            shipment = matrix[i][j].shipment,
                            assigned = HashMap(assigned),
                            parent = min
                        )
                        //Cost for ancestor nodes including current node
                        child.pathSS = min.pathSS + matrix[i][j].ss

                        //Calculate lower ss (that includes the path ss)
                        child.ss = child.pathSS +
                                getLowestSSAfterDriverAssignedShipment(matrix, i, child.assigned)
                        child.driverIndex = i
                        //Add child node to queue
                        priorityQueue.add(child)
                    }
                }
            }

        }

        return getBuildNodes(minOut)
    }

    private fun getLowestSSAfterDriverAssignedShipment(
        matrix: MutableList<MutableList<SuitabilityScore>>,
        driver: Int,
        assigned: HashMap<Shipment, Boolean>,
    ): Float {
        var cost = 0f;

        //Store available shipment
        val available = matrix[driver].associate { it.shipment to true }
            .toMutableMap()//mutableMapOf<Shipment, Boolean>()

        //Start new driver
        for (i in driver.plus(1)..n.minus(1)) {
            var min = Float.MAX_VALUE
            var minShipment: Shipment? = null

            //Do for each shipment
            matrix[i].forEachIndexed { j, it ->

                //If shipment is unassigned
                if (!assigned.containsKey(it.shipment) &&
                    available.containsKey(it.shipment) &&
                    it.ss < min
                ) {
                    //Store shipment number
                    minShipment = matrix[i][j].shipment
                    //Store cost
                    min = matrix[i][j].ss
                }

            }

            // Add cost of next driver
            cost += min
            minShipment?.let { sh ->
                available.remove(sh)
            }

        }

        //Return total cost
        return cost
    }


    /**
     * Build result from [Node] node
     * @return List of [Driver] drivers with assigned [Shipment]
     */
    private fun getBuildNodes(min: Node): List<Driver> {
        val driverList = mutableListOf<Driver>()
        var aux: Node? = min
        do {
            aux?.let {
                driverList.add(it.driver.copy(
                    shipment = it.shipment,
                    ss = it.driver.ss
                ))
                aux = it.parent
            }
            if (aux?.parent == null) aux = null //Do not include root
        } while (aux != null)

        return driverList.toList()
    }

    /**
     * Auxiliary node class
     */
    data class Node(
        var parent: Node? = null,
        var pathSS: Float = 0f,
        var ss: Float = 0f,
        var driver: Driver,
        var driverIndex: Int = 0,
        var shipment: Shipment,
        var assigned: HashMap<Shipment, Boolean> = HashMap(),
    ) {
        companion object {
            fun newNode(
                driver: Driver,
                driverIndex: Int,
                shipment: Shipment,
                assigned: HashMap<Shipment, Boolean> = HashMap(),
                parent: Node? = null,
            ): Node {
                val node = Node(
                    driver = driver,
                    shipment = shipment,
                    assigned = assigned,
                    parent = parent,
                    driverIndex = driverIndex
                )
                node.assigned[shipment] = true

                return node
            }

            fun dummy() = newNode(
                Driver(0, "DUMMY", null, null), -1,
                Shipment(0, ""))
        }
    }

}
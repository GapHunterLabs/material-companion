package com.acmecorp.inventory

import java.time.Instant

/**
 * Tracks warehouse stock levels and triggers reorder events when a SKU
 * drops below its configured threshold.
 */
class InventoryService(
    private val warehouseId: String,
    private val reorderThreshold: Int = 25,
) {
    private val stockLevels = mutableMapOf<String, Int>()

    fun receiveShipment(sku: String, quantity: Int, receivedAt: Instant) {
        require(quantity > 0) { "Shipment quantity must be positive, got $quantity" }
        val current = stockLevels.getOrDefault(sku, 0)
        stockLevels[sku] = current + quantity
        println("Received $quantity units of $sku at $warehouseId on $receivedAt")
    }

    fun reserveUnits(sku: String, quantity: Int): Boolean {
        val available = stockLevels[sku] ?: return false
        if (available < quantity) return false
        stockLevels[sku] = available - quantity
        if (stockLevels[sku]!! < reorderThreshold) {
            triggerReorderAlert(sku)
        }
        return true
    }

    private fun triggerReorderAlert(sku: String) {
        // TODO: publish to the reorder.requested Kafka topic instead of logging
        println("ALERT: $sku is below reorder threshold ($reorderThreshold units)")
    }

    companion object {
        const val DEFAULT_WAREHOUSE = "acme-east-1"
        val CRITICAL_SKUS = setOf("SKU-1001", "SKU-2044", "SKU-3390")
    }
}

package de.sakul6499.githubgameoff.engine

import java.awt.Graphics

object Screen: Updateable, Renderable {
    fun GetWidth(): Int = GameMain.gameConfig.width
    fun GetHeight(): Int = GameMain.gameConfig.height

    private val layers: MutableMap<Int, Layer> = mutableMapOf()

    private val debugLayer: DebugLayer = DebugLayer()
    val debugRenderingActive: Boolean
        get() = debugLayer.isActive
    fun setDebugRendering(active: Boolean) {
        debugLayer.isActive = active
    }

    private val mouseLayer: MouseLayer = MouseLayer()
    val mouseRenderingActive: Boolean
        get() = mouseLayer.isActive
    fun setMouseRendering(active: Boolean) {
        mouseLayer.isActive = active
    }

    init {
        registerLayer(debugLayer)
        registerLayer(mouseLayer)
    }

    fun registerLayer(layer: Layer) {
        if(layers.containsKey(layer.order)) {
            while (layers.containsKey(layer.order)) {
                layer.order++
            }
            println("Incremented order from $layer to ${layer.order}!")
        }
        layers.put(layer.order, layer)
    }

    fun removeLayer(order: Int) = layers.remove(order)
    fun removeLayer(layer: Layer) = removeLayer(layer.order)

    override fun update(delta: Long, alpha: Long) {
        layers.keys.sortedBy { it }.map { layers[it]!! }.filter { it.isActive }.forEach { it.update(delta, alpha) }
    }

    override fun render(graphics: Graphics) {
        layers.keys.sortedBy { it }.map { layers[it]!! }.filter { it.isActive }.forEach { it.render(graphics) }
    }
}
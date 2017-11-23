package de.sakul6499.githubgameoff.engine.graphics

import de.sakul6499.githubgameoff.engine.GameMain
import java.awt.Graphics

object Screen : Updateable, Renderable {
    fun GetWidth(): Int = GameMain.gameConfig.width
    fun GetHeight(): Int = GameMain.gameConfig.height

    private val layers: MutableMap<Int, BasicLayer> = mutableMapOf()

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

    fun registerLayer(layer: BasicLayer) {
        if (layers.containsKey(layer.order)) {
            while (layers.containsKey(layer.order)) {
                layer.order++
            }
            println("Incremented order from $layer to ${layer.order}!")
        }
        layers.put(layer.order, layer)
    }

    fun removeLayer(order: Int) = layers.remove(order)
    fun removeLayer(layer: BasicLayer) = removeLayer(layer.order)

    override fun update(delta: Long, alpha: Long) {
        layers.keys.sortedBy { it }.map { layers[it]!! }.filter { it.isActive && it is Layer }.forEach { (it as Layer).update(delta, alpha) }
    }

    override fun render(graphics: Graphics) {
        layers.keys.sortedBy { it }.map { layers[it]!! }.filter { it.isActive }.forEach {
            if (it is Layer) {
                it.render(graphics)
            } else {
                it.renderImage(graphics, (it as RenderOnceLayer).layer!!)
            }
        }
    }
}
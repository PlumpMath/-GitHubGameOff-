package de.sakul6499.githubgameoff.engine.asset

import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage

class Image(_bi: BufferedImage) : AbstractImage() {
    private var bi: BufferedImage = convertImage(_bi)

    override fun getImage(): BufferedImage = bi

    override fun rotate(theta: Double): Image {
        val at = AffineTransform()
        at.translate((bi.width / 2).toDouble(), (bi.height / 2).toDouble())
        at.rotate(theta)
        at.translate(-(bi.width / 2).toDouble(), -(bi.height / 2).toDouble())

        val _bi = bi
        bi = BufferedImage(_bi.width, _bi.height, BufferedImage.TYPE_INT_ARGB)
        val g = bi.createGraphics()
        g.drawImage(_bi, at, null)
        g.dispose()

        return this
    }
}
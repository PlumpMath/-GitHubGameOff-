package de.sakul6499.githubgameoff.engine.asset

import java.awt.image.BufferedImage

class StagedImage(private var images: Array<Image> = arrayOf()) : AbstractImage() {

    companion object {
        fun CreateRotationBasedStagedImage(vararg images: BufferedImage, thetaPerImage: Double): StagedImage {
            var arr: Array<Image> = arrayOf()
            for (i in 0 until images.size) {
                arr += Image(images[i]).rotate(thetaPerImage * i)
            }

            return StagedImage(arr)
        }

        fun CreateRotationBasedStagedImage(image: BufferedImage, loops: Int, thetaPerImage: Double): StagedImage {
            var arr: Array<Image> = arrayOf()
            for (i in 0 until loops) {
                arr += Image(image).rotate(thetaPerImage * i)
            }

            return StagedImage(arr)
        }
    }

    override fun getImage(): BufferedImage = getImage(0)
    fun getImage(index: Int): BufferedImage = images[index].getImage()
    fun getImages(): Array<Image> = images

    fun stages(): Int = images.size

    override fun rotate(theta: Double): StagedImage {
        images.forEach { it.rotate(theta) }
        return this
    }
}

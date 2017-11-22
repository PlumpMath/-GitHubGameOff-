package de.sakul6499.githubgameoff.engine.asset

import java.awt.image.BufferedImage

object Images {
    private val images: HashMap<String, AbstractImage> = hashMapOf()

    fun getImage(name: String): BufferedImage = getImageObject(name).getImage()
    fun getImage(name: String, index: Int): BufferedImage {
        val imageObject = getImageObject(name)
        return if (imageObject is StagedImage) {
            imageObject.getImage(index)
        } else {
            println("Warning: Using index on non staged image!")
            imageObject.getImage()
        }
    }

    fun getImageObject(name: String): AbstractImage = images[name] ?: throw IllegalAccessError("Image '$name' not found!")
    fun registerImage(name: String, image: AbstractImage) {
        images[name] = image
    }
}
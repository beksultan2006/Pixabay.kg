package com.template.pixabaykg.model

/**
 * Author: Dzhaparov Bekmamat
 */
data class PixabayModel(
    var hits: ArrayList<ImageModel>
)

data class ImageModel(
    var largeImageURL: String
)
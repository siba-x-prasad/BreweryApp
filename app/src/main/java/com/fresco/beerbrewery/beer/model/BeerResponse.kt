package com.fresco.beerbrewery.beer.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


class BeerResponse : ArrayList<BeerItem>()

@Keep
@Parcelize
data class BeerItem(
    val abv: Double?, // 4.5
    val attenuation_level: Double?, // 88.9
    val boil_volume: BoilVolume?,
    val brewers_tips: String?, // If you are struggling to get that authentic head retention increase your mash temperature to about 70°C.
    val contributed_by: String?, // Sam Mason <samjbmason>
    val description: String, // Imperial Wheat beer / Weizenbock brewed by a homesick German in leather trousers. Think banana bread, bubble gum and David Hasselhoff.
    val ebc: Int?, // 20
    val first_brewed: String?, // 03/2011
    val food_pairing: List<String>?,
    val ibu: Double?, // 41.5
    val id: Int?, // 1
    val image_url: String?, // https://images.punkapi.com/v2/keg.png
    val ingredients: Ingredients?,
    val method: Method?,
    val name: String?, // Rabiator
    val ph: Double?, // 4.4
    val srm: Double?, // 7.5
    val tagline: String?, // Imperial Wheat Beer
    val target_fg: Int, // 1010
    val target_og: Double?, // 1041.7
    val volume: Volume?
) : Parcelable {
    var isFavourite = 0
    val foodPairingStr: String
        get() = food_pairing?.joinToString(separator = ", ")!!

    val otherDetails: String
        get() = "Ph : $ph, srm $srm,  ibu : $ibu, target_fg : $target_fg, Target OG : $target_og"
}

@Keep
@Parcelize
data class BoilVolume(
    val unit: String?, // litres
    val value: Int? // 25
) : Parcelable {
    val boilVolume = "Boil Volume $value $unit"
}

@Keep
@Parcelize
data class Ingredients(
    val hops: List<Hop>?,
    val malt: List<Hop>?,
    val yeast: String? // Wyeast 3333 - German Wheat™
) : Parcelable

@Keep
@Parcelize
data class Method(
    val fermentation: Fermentation?,
    val mash_temp: List<MashTemp>?,
    val twist: String?,
) : Parcelable

@Keep
@Parcelize
data class Volume(
    val unit: String?, // litres
    val value: Int? // 20
) : Parcelable {
    var beerVolume: String = ""
        get() = "Volume : $value $unit"
}

@Keep
@Parcelize
data class Hop(
    val add: String? = "", // start
    val amount: Amount?,
    val attribute: String? = "", // bitter
    val name: String? // Columbus
) : Parcelable {
    var isWeighed: Boolean? = false
    val measurement: String?
        get() = "${amount?.value} ${amount?.unit}"
    val roundValueAmount: Int?
        get() = amount?.value?.toInt()
}

@Keep
@Parcelize
data class Malt(
    val amount: AmountX?,
    val name: String? // Extra Pale
) : Parcelable {
    val details = "$name  ${amount?.value} ${amount?.unit}"
}

@Keep
@Parcelize
data class Amount(
    val unit: String?, // grams
    val value: Double? // 12.5
) : Parcelable

@Keep
@Parcelize
data class AmountX(
    val unit: String?, // kilograms
    val value: Double? // 5.63
) : Parcelable

@Keep
@Parcelize
data class Fermentation(
    val temp: Temp?
) : Parcelable

@Keep
@Parcelize
data class MashTemp(
    val duration: Int?, // 60
    val temp: TempX?
) : Parcelable

@Keep
@Parcelize
data class Temp(
    val unit: String?, // celsius
    val value: Int? // 21
) : Parcelable

@Keep
@Parcelize
data class TempX(
    val unit: String?, // celsius
    val value: Int? // 64
) : Parcelable
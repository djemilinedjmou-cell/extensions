package com.egydead

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*

class EgyDeadProvider : MainAPI() {

    override var mainUrl = "https://egydead.fyi"
    override var name = "EgyDead"
    override var lang = "ar"

    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries,
        TvType.Anime
    )

    override val mainPage = mainPageOf(
        "$mainUrl/home" to "أحدث الإضافات"
    )
}

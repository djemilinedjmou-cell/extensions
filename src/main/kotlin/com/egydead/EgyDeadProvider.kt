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
        "$mainUrl/home" to "أحدث الأفلام"
    )

    override suspend fun getMainPage(
        page: Int,
        request: MainPageRequest
    ): HomePageResponse {

        val document = app.get(request.data).document

        val items = document.select(".movie-item").mapNotNull { element ->
            val link = element.selectFirst("a")?.attr("href") ?: return@mapNotNull null
            val title = element.selectFirst("a")?.attr("title") ?: "بدون عنوان"
            val poster = element.selectFirst("img")?.attr("src")

            newSearchResponse(
                title,
                fixUrl(link),
                TvType.Movie
            ) {
                posterUrl = fixUrl(poster)
            }
        }

        return newHomePageResponse(
            listOf(HomePageList(request.name, items)),
            false
        )
    }
}

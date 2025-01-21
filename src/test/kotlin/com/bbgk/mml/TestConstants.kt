package com.bbgk.mml

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.musicList.dto.MusicForm

abstract class TestConstants {
    companion object {
        // IDs
        const val MEMBER_ID = 1L
        const val PLAYLIST_ID = 1L
        const val MUSIC_ID = 1L
        const val PLAYLIST_MUSIC_ID = 1L

        // Page Config
        const val PAGE = 0

        // Test Data
        val member = Member("test", "test")
        val musicForm = MusicForm("music", "artist", "url")

        // Common test data factory methods
        fun createTestMusics() = listOf(
            Music("title1", "artist1", "url1"),
            Music("title2", "artist2", "url2"),
            Music("title3", "artist3", "url3")
        )
    }
}
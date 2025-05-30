package com.example.financeapp.ui.screens

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.financeapp.R
import com.example.financeapp.ui.data.local.OnboardingPage
import com.example.financeapp.ui.theme.MatuleTheme


@Composable
fun SliderPage(){
    val colorlist = listOf(MatuleTheme.colors.BackSlider1, MatuleTheme.colors.BackSlider2)
    val pageState  = rememberPagerState( pageCount = {4})
    val coroutineScope = rememberCoroutineScope ()
    val pages = listOf(
        OnboardingPage(
            title = "Поздоровайтесь с вашим новым финансовым трекером",
            description = "Вы замечательны тем, что сделали первый шаг к улучшению контроля над своими деньгами и финансовыми целями",
            image = R.drawable.__2025_05_30_152055_2_
        ),
        OnboardingPage(
            title = "Поздоровайтесь с вашим новым финансовым трекером",
            description = "Вы замечательны тем, что сделали первый шаг к улучшению контроля над своими деньгами и финансовыми целями",
            image = R.drawable._1_
        ),
        OnboardingPage(
            title = "Поздоровайтесь с вашим новым финансовым трекером",
            description = "Вы замечательны тем, что сделали первый шаг к улучшению контроля над своими деньгами и финансовыми целями",
            image = R.drawable.qwe
        ),
        OnboardingPage(
            title = "Поздоровайтесь с вашим новым финансовым трекером",
            description = "Вы замечательны тем, что сделали первый шаг к улучшению контроля над своими деньгами и финансовыми целями",
            image = R.drawable.qwe123_1_
        )
    )
}
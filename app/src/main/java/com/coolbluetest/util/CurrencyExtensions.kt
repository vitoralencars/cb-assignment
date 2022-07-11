package com.coolbluetest.util

import java.text.DecimalFormat

fun Double.formatCurrency(): String = DecimalFormat("#,###.00").format(this)

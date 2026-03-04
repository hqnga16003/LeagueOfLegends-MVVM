fun findTwoSumBruteForce(n: Int): String {
    for (i in 0..n) {
        for (j in 0..n) {
            if (i + j == n) {
                return "Tìm thấy: $i + $j = $n"
            }
        }
    }
    return "Không tìm thấy"
}

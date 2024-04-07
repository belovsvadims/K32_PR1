// 5.uzd
public class AlphaBetaAlgorithm {
    public static int findBestMove() {
        // Sāk ar vismazāko alfa un vislielāko beta vērtību
        return alphaBeta(Game.currentSelectedNode, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    }

    // alfa-beta algoritma implementācija
    private static int alphaBeta(GameTreeNodeMinMax node, int alpha, int beta, boolean maximizingPlayer) {
        // Ja nekas nav nodots, atgriez 0
        if (node == null)
            return 0;

        // Ja sasniegts beigu līmenis, atgriež nodošanas novērtējumu
        if (node.getDividingBy2() == null && node.getDividingBy3() == null) {
            return node.getEvaluationScore();
        }

        // Ja šobrīd maksimizējamā spēlētāja gājiens
        if (maximizingPlayer) {
            int bestDivisor = 0; // Labākās dalisanas opcijas
            int value = Integer.MIN_VALUE; // Vērtība sākumā ir minimāla
            // Apmeklē katru iespējamo child
            if (node.getDividingBy2() != null) {
                int childValue = alphaBeta(node.getDividingBy2(), alpha, beta, false);
                // Ja child vērtība ir lielāka par pašreizējo vērtību, atjauno vērtību un labāko dalisanas opciju
                if (childValue > value) {
                    value = childValue;
                    bestDivisor = 2;
                }
                alpha = Math.max(alpha, value); // Atjauno alfa vērtību
                // Ja beta vērtība ir mazāka vai vienāda ar alfa vērtību, pārtrauc ciklu
                if (beta <= alpha)
                    return bestDivisor;
            }
            if (node.getDividingBy3() != null) {
                int childValue = alphaBeta(node.getDividingBy3(), alpha, beta, false);
                if (childValue > value) {
                    value = childValue;
                    bestDivisor = 3;
                }
                alpha = Math.max(alpha, value);
                if (beta <= alpha)
                    return bestDivisor;
            }
            return bestDivisor;
        } else { // Ja šobrīd minimizējamā spēlētāja gājiens
            int bestDivisor = 0; // Labākās dalisanas opcijas
            int value = Integer.MAX_VALUE; // Vērtība sākumā ir maksimāla
            // Apmeklē katru iespējamo child
            if (node.getDividingBy2() != null) {
                int childValue = alphaBeta(node.getDividingBy2(), alpha, beta, true);
                // Ja bērna vērtība ir mazāka par pašreizējo vērtību, atjauno vērtību un labāko dalisanas opciju
                if (childValue < value) {
                    value = childValue;
                    bestDivisor = 2;
                }
                beta = Math.min(beta, value); // Atjauno beta vērtību
                // Ja beta vērtība ir mazāka vai vienāda ar alfa vērtību, pārtrauc ciklu
                if (beta <= alpha)
                    return bestDivisor;
            }
            if (node.getDividingBy3() != null) {
                int childValue = alphaBeta(node.getDividingBy3(), alpha, beta, true);
                if (childValue < value) {
                    value = childValue;
                    bestDivisor = 3;
                }
                beta = Math.min(beta, value);
                if (beta <= alpha)
                    return bestDivisor;
            }
            return bestDivisor;
        }
    }
}


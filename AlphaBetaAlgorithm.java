// Alfa-Beta algoritma implementācija
public class AlphaBetaAlgorithm {
    // Metode, kas atrod labāko gājienu (dalītāju) izmantojot Alfa-Beta algoritmu
    public static int findBestMove() {
        // Sākotnējā vērtība ir minimāla
        int bestValue = Integer.MIN_VALUE;
        // Labākais dalītājs
        int bestDivisor = 0;

        // Izvērtējam pašreizējā mezgla stāvokli
        EvaluationFunction.evaluateState(Game.currentSelectedNode);

        // Mezgla child
        GameTreeNode child2 = Game.currentSelectedNode.getDividingBy2();
        GameTreeNode child3 = Game.currentSelectedNode.getDividingBy3();

        // Izvēlamies labāko dalītāju pamatojoties uz bērnu novērtējuma rezultātiem
        if (child2 == null) {
            bestDivisor = 3; // Ja nav bērnu, tad labākais dalītājs ir 3
        } else if (child3 == null) {
            bestDivisor = 2; // Ja ir tikai viens bērns, tad labākais dalītājs ir 2
        } else {
            // Ja ir divi bērni, tad izvēlamies labāko rezultātu no tiem
            int value2 = alphaBeta(child2, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            int value3 = alphaBeta(child3, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            if (value2 > value3) {
                bestDivisor = 2; // Ja child2 ir labāks, tad labākais dalītājs ir 2
            } else {
                bestDivisor = 3; // Ja child3 ir labāks, tad labākais dalītājs ir 3
            }
        }

        return bestDivisor; // Atgriežam labāko dalītāju
    }

    // Alfa-Beta algoritma rekursīvā implementācija
    private static int alphaBeta(GameTreeNode node, int alpha, int beta, boolean maximizingPlayer) {
        if (node == null)
            return 0; // ja nekas netiek nodots, atgriez 0

        if (node.getDividingBy2() == null && node.getDividingBy3() == null) {
            return node.getEvaluationScore(); // Ja tas ir strupcels, atgriežam tā novērtējumu
        }

        // Ja max  gājiens
        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE; // Sākumā vērtība ir minimāla
            if (node.getDividingBy2() != null) {
                value = Math.max(value, alphaBeta(node.getDividingBy2(), alpha, beta, false));
                alpha = Math.max(alpha, value);
                if (beta <= alpha) {
                    // tiek nodots novērtējuma rezultāts šim node(tas nav obligāti)
                    node.setEvaluationScore(value);
                    return value;
                }
            }
            if (node.getDividingBy3() != null) {
                value = Math.max(value, alphaBeta(node.getDividingBy3(), alpha, beta, false));
                alpha = Math.max(alpha, value);
                if (beta <= alpha) {
                    // tiek nodots novērtējuma rezultāts šim node(tas nav obligāti)
                    node.setEvaluationScore(value);
                    return value;
                }
            }
            return value;
        } else { // Ja min gājiens
            int value = Integer.MAX_VALUE; // Sākumā vērtība ir maksimāla
            if (node.getDividingBy2() != null) {
                value = Math.min(value, alphaBeta(node.getDividingBy2(), alpha, beta, true));
                beta = Math.min(beta, value);
                if (beta <= alpha) {
                    // tiek nodots novērtējuma rezultāts šim node(tas nav obligāti)
                    node.setEvaluationScore(value);
                    return value;
                }
            }
            if (node.getDividingBy3() != null) {
                value = Math.min(value, alphaBeta(node.getDividingBy3(), alpha, beta, true));
                beta = Math.min(beta, value);
                if (beta <= alpha) {
                    // tiek nodots novērtējuma rezultāts šim node(tas nav obligāti)
                    node.setEvaluationScore(value);
                    return value;
                }
            }
            return value;
        }
    }
}

package prova.questões.prova4;

public class AdvancedAssertTest {
    public static void main(String[] args) {

        // Teste 1 - Double com tolerância
        try {
            AdvancedAssert.assertDoubleEquals(10.0, 10.01, 0.05,
                "Falha no teste de double");
            System.out.println("Teste 1 OK!");
        } catch (AssertionError e) {
            System.out.println("Teste 1 Falhou: " + e.getMessage());
        }

        // Teste 2 - Coleções
        try {
            String[] expected = {"A", "B", "C"};
            String[] actual   = {"C", "B", "A"};
            AdvancedAssert.assertCollectionEquals(expected, actual,
                "Falha no teste de coleção");
            System.out.println("Teste 2 OK!");
        } catch (AssertionError e) {
            System.out.println("Teste 2 Falhou: " + e.getMessage());
        }

        // Teste 3 - Objetos
        try {
            Pessoa p1 = new Pessoa("Ana", 25);
            Pessoa p2 = new Pessoa("Ana", 25);
            AdvancedAssert.assertObjectEquals(p1, p2,
                "Falha no teste de objeto");
            System.out.println("Teste 3 OK!");
        } catch (AssertionError e) {
            System.out.println("Teste 3 Falhou: " + e.getMessage());
        }

        // Teste 4 - Timeout
        try {
            AdvancedAssert.assertTimeout(1000, () -> {
                for (int i = 0; i < 1_000_000; i++); // rápido
            }, "Falha no teste de timeout");
            System.out.println("Teste 4 OK!");
        } catch (AssertionError e) {
            System.out.println("Teste 4 Falhou: " + e.getMessage());
        }
    }
}

// Classe auxiliar usada no Teste 3
class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}

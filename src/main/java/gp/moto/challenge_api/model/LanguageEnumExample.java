package gp.moto.challenge_api.model;

/**
 * Exemplo de como usar o enum LanguageEnumPreferences
 */
public class LanguageEnumExample {

    public static void exemploUso() {
        // 1. Criando um usuário com preferência de idioma
        Usuario usuario = new Usuario();
        usuario.setNmUsuario("João Silva");
        usuario.setNmEmail("joao@email.com");
        usuario.setLanguageEnumPreference(LanguageEnumPreferences.PTBR); // Define como português brasileiro

        // 2. Alterando a preferência de idioma
        usuario.setLanguageEnumPreference(LanguageEnumPreferences.EN); // Muda para inglês

        // 3. Verificando a preferência atual
        LanguageEnumPreferences idiomaAtual = usuario.getLanguageEnumPreference();

        if (idiomaAtual == LanguageEnumPreferences.PTBR) {
            System.out.println("Idioma: Português Brasileiro");
        } else if (idiomaAtual == LanguageEnumPreferences.ES) {
            System.out.println("Idioma: Español");
        } else if (idiomaAtual == LanguageEnumPreferences.EN) {
            System.out.println("Idioma: English");
        }

        // 4. Valores possíveis do enum
        LanguageEnumPreferences[] valoresPossiveis = LanguageEnumPreferences.values();
        for (LanguageEnumPreferences valor : valoresPossiveis) {
            System.out.println("Valor: " + valor);
            System.out.println("Nome: " + valor.name());
        }

        // 5. Convertendo string para enum (útil para APIs)
        String idiomaString = "EN";
        LanguageEnumPreferences idiomaEnum = LanguageEnumPreferences.valueOf(idiomaString.toUpperCase());
        System.out.println("Enum convertido: " + idiomaEnum);
    }
}
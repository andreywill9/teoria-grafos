package Connection;

import java.io.File;
import java.nio.file.Files;

public class ArquivoUtils {

  public static String arquivoParaString(String caminhoResources) throws Exception {
    File arquivo = new File(Thread.currentThread().getContextClassLoader().getResource(caminhoResources).getFile());
    return new String(Files.readAllBytes(arquivo.toPath()));
  }

}

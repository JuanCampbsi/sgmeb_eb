package br.com.project.util.all;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Classe utilitaria para conversão de byte[]
 * @author Juan Campos
 *
 */
public class BytesUtilJalis {

	/**
	 * Converte Objeto em byte[]
	 * @param obj
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] toByteArray(Object obj) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		}
		finally {
			if (oos != null) {
				oos.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		return bytes;
	}

	/**
	 * Conveter byte[] para Object
	 * @param bytes
	 * @return Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		}
		finally {
			if (bis != null) {
				bis.close();
			}
			if (ois != null) {
				ois.close();
			}
		}
		return obj;
	}

	/**
	 * Converte byte[] para String
	 * @param bytes
	 * @return String
	 */
	public static String toString(byte[] bytes) {
		return new String(bytes);
	}

	/**
	 * Gera arquivo no servidor
	 * @param pdf
	 * @param httpServletRequest
	 * @throws Exception
	 */
	public static void gerarArquivo(byte[] pdf, String caminhoFile) throws Exception {
		
		File file = new File(caminhoFile + File.separator + "arquivo.pdf");
		FileOutputStream fileOuputStream = new FileOutputStream(file);
		fileOuputStream.write(pdf);
		fileOuputStream.flush();
		fileOuputStream.close();
	}

}

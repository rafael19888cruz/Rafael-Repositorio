package edu.br.utfpr.http.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.br.utfpr.http.utils.request.Request;
import edu.br.utfpr.http.utils.response.Response;
import edu.br.utfpr.http.utils.response.ResponseFactory;

/**
 * Servidor HTTP simples
 * 
 */
public class Servidor {

	private final static Logger logger = Logger.getLogger(Servidor.class
			.toString());

	private String host;
	private int port;

	/**
	 * Construtor do servidor de HTTP
	 * 
	 * @param host
	 *            host do servidor
	 * @param port
	 *            porta do servidor
	 */
	public Servidor(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	/**
	 * Inicia o servidor e fica escutando no endereço e porta especificada no
	 * construtor
	 */
	public void serve() {
		ServerSocket serverSocket = null;

		logger.info("Iniciando servidor no endereço: " + this.host
				+ ":" + this.port);		
		
		try {
			// Cria a conexão servidora
			serverSocket = new ServerSocket(port, 0, InetAddress.getByName(host));
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Erro ao iniciar servidor!", e);
			return;
		}
		logger.info("Conexão com o servidor aberta no endereço: " + this.host
				+ ":" + this.port);

		// Fica esperando pela conexão cliente
		while (true) {
			logger.info("Aguardando conexões...");
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();

				// Realiza o parse da requisição recebida
				String requestString = convertStreamToString(input);
				logger.info("Conexão recebida. Conteúdo:\n" + requestString);
				Request request = new Request();
				request.parse(requestString);

				// recupera a resposta de acordo com a requisicao
				Response response = ResponseFactory.createResponse(request);
				
				String responseString = "";
				File arquivo = new File(System.getProperty("user.dir")+"/src/main/java/edu/br/utfpr/learquivos"+request.getUri());
				
				if(arquivo.exists()) {
					if(request.getUri().equals("/")) {
						responseString = response.respond();
					}else {
						responseString = response.respond(arquivo);
					}
				}else {
					responseString = response.responde();
				}
				
				logger.info("Resposta enviada. Conteúdo:\n" + responseString);
				output.write(responseString.getBytes());

				// Fecha a conexão
				socket.close();

			} catch (Exception e) {
				logger.log(Level.SEVERE, "Erro ao executar servidor!", e);
				continue;
			}
		}
	}

	private String convertStreamToString(InputStream is) {

		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[2048];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is));
				int i = reader.read(buffer);
				writer.write(buffer, 0, i);
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Erro ao converter stream para string", e);
				return "";
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	public static void main(String[] args) {
		Servidor server = new Servidor("localhost", 8091);
		

		server.serve();
	}

}

package persistence;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import entity.Cliente;
import service.ServicesInterface;

public class ClienteDao {

	private static final Logger log = Logger.getLogger(ClienteDao.class);

	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();

	public List<Cliente> consultaClientes(){

		List<Cliente> lst = new ArrayList<Cliente>();

		try {
			String path = "https://cadastrogrupowlrest.herokuapp.com/";

			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
			ServicesInterface proxy = target.proxy(ServicesInterface.class);

			Response response = proxy.consulta();

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				log.error("Erro status "+response.getStatus()+" ao chamar rest api "+response.readEntity(String.class));

				throw new RuntimeException("Erro ao consultar clientes");
			}

			String json =  response.readEntity(String.class);

			response.close();

			Type rootType = new TypeToken<List<Cliente>>(){}.getType();
			lst = gson.fromJson(json, rootType);


		} catch (Exception e) {
			log.error("Erro ao consultar clientes: "+e.getMessage());
		} 

		return lst;
	}

	public String insereCliente(Cliente cliente) {

		String json = null;

		try {

			String path = "https://cadastrogrupowlrest.herokuapp.com/";

			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
			ServicesInterface proxy = target.proxy(ServicesInterface.class);

			String jsonInString = gson.toJson(cliente);

			Response response = proxy.insere(jsonInString);

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				log.error("Erro status "+response.getStatus()+" ao chamar rest api "+response.readEntity(String.class));
				throw new RuntimeException("Erro ao inserir");
			}

			json =  response.readEntity(String.class);
			log.info("Resposta: "+json);
			response.close();

		} catch (Exception e) {
			log.error("ERRO: "+e.getMessage());
		}

		return json;

	}

	public String atualizaCliente(Cliente cliente) {

		String json = null;

		try {

			String path = "https://cadastrogrupowlrest.herokuapp.com/";

			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
			ServicesInterface proxy = target.proxy(ServicesInterface.class);

			String jsonInString = gson.toJson(cliente);

			Response response = proxy.atualiza(jsonInString);

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				log.error("Erro status "+response.getStatus()+" ao chamar rest api "+response.readEntity(String.class));
				throw new RuntimeException("Erro ao inserir");
			}

			json =  response.readEntity(String.class);
			log.info("Resposta: "+json);
			response.close();

		} catch (Exception e) {
			log.error("ERRO: "+e.getMessage());
		}

		return json;

	}
	
	public String deletaCliente(int id) {

		String json = null;

		try {

			String path = "https://cadastrogrupowlrest.herokuapp.com/";

			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
			ServicesInterface proxy = target.proxy(ServicesInterface.class);

			Response response = proxy.deleta(id);

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				log.error("Erro status "+response.getStatus()+" ao chamar rest api "+response.readEntity(String.class));
				throw new RuntimeException("Erro ao deletar");
			}

			json =  response.readEntity(String.class);
			log.info("Resposta: "+json);
			response.close();

		} catch (Exception e) {
			log.error("ERRO: "+e.getMessage());
		}

		return json;

	}


}

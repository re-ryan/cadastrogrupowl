package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entity.Cliente;
import persistence.ClienteDao;

@ViewScoped
@ManagedBean(name="ClienteBean")
public class ClienteBean extends BaseBean{

	private Cliente cliente;
	private ClienteDao clienteDao = new ClienteDao();
	private List<Cliente> clientes;
	private Date minDate;
	private Date maxDate = new Date();

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		clientes = new ArrayList<Cliente>();
		setMinDate();
		
	}

	public void cadastra() {

		String resultado = clienteDao.insereCliente(cliente);

		if(resultado.contains("sucesso")) {
			addMessage(resultado, "", "info");
			cliente = new Cliente();

		}
		else
			addMessage(resultado, "", "error");
		

	}
	public void atualiza() {

		String resultado = clienteDao.atualizaCliente(cliente);

		if(resultado.contains("sucesso")) {
			addMessage(resultado, "", "info");
			carregaClientes();
		}
		else
			addMessage(resultado, "", "error");

	}
	public void deleta() {

		String resultado = clienteDao.deletaCliente(cliente.getCodCliente());

		if(resultado.contains("sucesso")) {
			addMessage(resultado, "", "info");
			carregaClientes();
		}
		else
			addMessage(resultado, "", "error");

	}

	public void carregaClientes() {
		clientes = clienteDao.consultaClientes();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Date getMinDate() {

		return minDate;
	}

	public void setMinDate() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(1900, 0, 1);

		this.minDate = calendar.getTime();
	}

	public Date getMaxDate() {

		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}

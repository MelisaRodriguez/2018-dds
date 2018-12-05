package main.server;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;
import java.util.stream.Collectors;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.Simplex;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.Sony;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoClientes;
import spark.Spark;

public class Server {

	public static void main(String[] args) {
		Bootstrap.init();
		setCrons();
		// DebugScreen.enableDebugScreen();
		Spark.port(getHerokuAssignedPort());
		Router.configure();
	}

	static void setCrons() 
	{
		try {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        JobDetail jobDetail = newJob(SimplexJob.class).build();
        Trigger trigger = newTrigger().withSchedule(simpleSchedule().withIntervalInHours(24).repeatForever()).build();
        scheduler.scheduleJob(jobDetail, trigger);
        
        Scheduler scheduler2 = StdSchedulerFactory.getDefaultScheduler();
        scheduler2.start();
        JobDetail jobDetail2 = newJob(DispositivosJob.class).build();
        Trigger trigger2 = newTrigger().withSchedule(simpleSchedule().withIntervalInHours(4).repeatForever()).build();
        scheduler.scheduleJob(jobDetail2, trigger2);
        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
	}

	public static class SimplexJob implements Job{

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			
			List<Cliente> clientes = RepoClientes.getInstanceOfSingleton().getEntidades();
			Simplex simplex = new Simplex(612);
			clientes.stream().filter(c -> c.getAhorroAutomatico()).forEach(c -> simplex.optimizacionAutomatica(c));	
		}
	}
		
	public static class DispositivosJob implements Job {
		
		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			List<Cliente> clientes = RepoClientes.getInstanceOfSingleton().getEntidades();
			List<DispositivoInteligente> dispositivos = clientes.stream().flatMap(c -> c.getDispositivosInteligentes().stream()).collect(Collectors.toList());
			dispositivos.stream().forEach(d -> d.setFabricante(new Fabricante("Sony", new Sony())));
			dispositivos.stream().forEach(d -> d.agregarNuevoRegistroDeConsumo());
		}
	}
}
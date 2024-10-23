package seguimientoEstudiantes;

import java.util.HashMap;
import java.util.Map;

import actividades.Examen;
import preguntas.PreguntaAbierta;
import user.Estudiante;

public class SeguimientoExamen extends SeguimientoActividad{
	
	private float nota;
	private Map<PreguntaAbierta, String> respuestas;
	
	public SeguimientoExamen(Examen examen, Estudiante estudiante) {
		super(examen, estudiante);
		this.nota = -1;
		this.respuestas = new HashMap<PreguntaAbierta, String>();
		
		for(PreguntaAbierta pregunta: examen.getPreguntas()) {
			
			this.respuestas.put(pregunta, "");
			
		}
		
		
		
	}

	public float getNota() {
		return nota;
	}
	

	public void setNota(float nota) {
		this.nota = nota;
	}
	
	public void actualizarEstadoEnviado() {
		this.setEstado("Enviado");
	}
	
	public void actualizarEstadoCompletado() {
		this.setEstado("Completado");
	}

	public Map<PreguntaAbierta, String> getRespuestas() {
		return respuestas;
	}
	
	public void registrarPregunta(PreguntaAbierta pregunta, String respuesta) {
		
		getRespuestas().replace(pregunta, respuesta);
		
	}


	
	public void actualizarEstado(boolean esExitoso) {
		if (esExitoso) {
			this.setEstado("Exitoso");
		}
		
		else {
			this.setEstado("No exitoso");
		}
		
	}
		
}
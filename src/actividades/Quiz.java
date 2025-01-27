package actividades;

import java.util.Date; 
import java.util.List;

import exceptions.ModificarPreguntasQuizException;
import preguntas.PreguntaCerrada;
import user.Profesor;

public class Quiz extends Actividad {
	private static String TIPO = "Quiz";
    private double calificacionMinima;
    private List<PreguntaCerrada> preguntas;
    private int numPreguntas;

    // Constructor
    public Quiz(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos,
    		Date fechaLimite, Profesor profesorCreador, double calificacionMinima, List<PreguntaCerrada> preguntas) 
    
    {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador, TIPO);
        this.calificacionMinima = calificacionMinima;
        this.preguntas = preguntas;
        this.numPreguntas = preguntas.size();
    }
    
    public int getNumPreguntas() {
		return numPreguntas;
	}

	public double getCalificacionMinima() {
        return calificacionMinima;
    }

    public void setCalificacionMinima(double calificacionMinima) {
        this.calificacionMinima = calificacionMinima;
    }
    
    @Override
    public String getTipoActividad() {
    	return TIPO;
    }

	public List<PreguntaCerrada> getPreguntas() {
		return preguntas;
	}
	
	public void agregarPregunta(PreguntaCerrada pregunta) throws ModificarPreguntasQuizException {
		
		if (preguntas.contains(pregunta)) {
			
			throw new ModificarPreguntasQuizException (pregunta, "Agregar");
			
		}
		
		else {
			preguntas.add(pregunta);
		}
		
	}
	
	public void eliminarPregunta(PreguntaCerrada pregunta) throws ModificarPreguntasQuizException {
		
		if (!preguntas.contains(pregunta)) {
			
			throw new ModificarPreguntasQuizException (pregunta, "Eliminar");
			
		}
		
		else {
			preguntas.remove(pregunta);
		}
		
	}
    
    
    
}







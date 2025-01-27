package actividades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.ActividadPreviaCiclicoException;
import exceptions.ActividadSeguimientoCiclicoException;
import exceptions.ModificarActividadesPreviasException;
import exceptions.ModificarActividadesSeguimientoException;
import exceptions.ModificarObjetivosException;
import user.Profesor;

public abstract class Actividad {
	public final static String BAJO = "Principiante";
	public final static String MEDIO = "Intermedio";
	public final static String ALTO = "Avanzado";
	private String tipo;
    private String titulo;
    private String descripcion;
    private List<String> objetivos;
    private String nivelDificultad;
    private String loginProfesorCreador;
    private String nombreProfesorCreador;
    private int duracionMinutos;
    private Date fechaLimite;
    private List<String> resenas; 
    private double rating; 
    private int contadorRatings; 
    private List<Actividad> actividadesPrevias;
    private List<Actividad> actividadesSeguimiento;

    // Constructor
    public Actividad(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite, Profesor profesorCreador, String tipoActividad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivos = objetivos;
        this.nivelDificultad = nivelDificultad;
        this.loginProfesorCreador = profesorCreador.getLogin();
        this.nombreProfesorCreador = profesorCreador.getNombre();
        this.duracionMinutos = duracionMinutos;
        this.fechaLimite = fechaLimite;
        this.rating = 0.0d	; 
        this.resenas = new ArrayList<String>(); 
        this.contadorRatings = 0;
        this.actividadesPrevias = new ArrayList<Actividad>(); 
        this.actividadesSeguimiento = new ArrayList<>();
        this.tipo = tipoActividad;
    }

    // Getters, Setters y Actualizaciones
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getObjetivos() {
        return objetivos;
    }

    private void setObjetivos(List<String> objetivos) {
        this.objetivos = objetivos;
    }
  
	
	public void agregarObjetivo(String objetivo) throws ModificarObjetivosException {
		List<String> objetivos = this.getObjetivos();
		if (objetivos.contains(objetivo)) {
			throw new ModificarObjetivosException(objetivo, "Agregar", "Actividad");
		}
		
		else {
			objetivos.add(objetivo);
			this.setObjetivos(objetivos);
		}
		
	}
	
	public void eliminarObjetivo(String objetivo) throws ModificarObjetivosException {
		List<String> objetivos = this.getObjetivos();
		if (!objetivos.contains(objetivo)) {
			throw new ModificarObjetivosException(objetivo, "Eliminar", "Actividad");
		}
		
		else {
			objetivos.remove(objetivo);
			this.setObjetivos(objetivos);
		}
		
	}
    

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }



    public List<String> getResenas() {
        return resenas;
    }
    
    public void agregarResena(String resena) {
    	this.getResenas().add(resena);
    }

    public double getRating() {
        return rating;
    }

    private void setRating(double rating) {
        this.rating = rating;
    }
    
    public void actualizarRating (double nuevoRating) {
    	
    	int numRatings = this.getContadorRatings();
    	
    	if (numRatings == 0) {
    		this.setRating(nuevoRating);
    		this.setContadorRatings(numRatings+1);
    	}
    	
    	else {
    		int numRatingsActualizado = numRatings+1;
    		double ratingActual = this.getRating();
    		

    		double ratingActualizado = ratingActual*((double) numRatings/numRatingsActualizado)+ nuevoRating*(1.0/numRatingsActualizado);
    		
    		this.setRating(ratingActualizado);
    		this.setContadorRatings(numRatings+1);
    	}
    	
    	
    }
    
    
    
    public String getLoginProfesorCreador() {
 		return loginProfesorCreador;
 	}
    
    public String getNombreProfesorCreador() {
    	return nombreProfesorCreador;
    }
    
    public int getContadorRatings() {
		return contadorRatings;
	}
    
	private void setContadorRatings(int contadorRatings) {
		this.contadorRatings = contadorRatings;
	}
    
    
    
    // Métodos para gestionar actividades previas y de seguimiento


	public void agregarActividadPrevia(Actividad actividad) throws ModificarActividadesPreviasException, ActividadPreviaCiclicoException {
		boolean check1 = this.actividadesPrevias.contains(actividad);
		boolean check2 = actividad.revisarAgregarActividadesPrevias(this);
		if (check1 == true ) {
			throw new ModificarActividadesPreviasException(actividad, "Agregar");
		}
		
		else if (check2 == false) {
			throw new ActividadPreviaCiclicoException(this, actividad);
		}
		else {
			
		actividadesPrevias.add(actividad);
		}
	}
	
	public boolean revisarAgregarActividadesPrevias(Actividad actividadAgregar) {
		
        if (this.actividadesPrevias != null) {
            for (Actividad actividad : this.actividadesPrevias) {
                if (actividad.equals(actividadAgregar)) {
                    return false; 
                }

                if (!actividad.revisarAgregarActividadesPrevias(actividadAgregar)) {
                    return false; 
                }
            }
        }
  
        return true;
    }
	

    public void eliminarActividadPrevia(Actividad actividad) throws ModificarActividadesPreviasException {
    	if (actividadesPrevias.contains(actividad)) {
    		actividadesPrevias.remove(actividad);
    	}
        
    	else {
    		throw new ModificarActividadesPreviasException(actividad, "Agregar");
    	}
    }
    
    

    public List<Actividad> getActividadesPrevias() {
        return actividadesPrevias;
    }
    
	public void agregarActividadSeguimiento(Actividad actividad) throws ModificarActividadesSeguimientoException, ActividadSeguimientoCiclicoException {
        boolean check = revisarAgregarActividadesSeguimiento(this, actividad);
		if (actividadesSeguimiento.contains(actividad)) {
        	throw new ModificarActividadesSeguimientoException(actividad, "Agregar");
        }
        else if (check == false){
        	throw new ActividadSeguimientoCiclicoException(this, actividad); 
        }
		
        else {
        	actividadesSeguimiento.add(actividad);
        }
    }
	
	public boolean revisarAgregarActividadesSeguimiento(Actividad actividadPrincipal, Actividad actividadAgregar) {
		
		
        if (actividadAgregar.getActividadesSeguimiento() != null) {
            for (Actividad actividad : actividadAgregar.getActividadesSeguimiento() ) {
                if (actividad.equals(actividadPrincipal)) {
                    return false; 
                }

                if (!actividad.revisarAgregarActividadesSeguimiento(actividadPrincipal, actividadAgregar)) {
                    return false; 
                }
            }
        }
  
        return true;
    }
	
	

    public void eliminarActividadSeguimiento(Actividad actividad) throws ModificarActividadesSeguimientoException {
    	if (actividadesSeguimiento.contains(actividad)) {
    		actividadesSeguimiento.remove(actividad);
    	}
        
    	else {
    		throw new ModificarActividadesSeguimientoException(actividad, "Agregar");
    	}
    }



    public List<Actividad> getActividadesSeguimiento() {
        return actividadesSeguimiento;
    }
    
    public String getTipoActividad() {
    	return tipo;
    	
    }

    // Métodos Adicionales


    
    public String getIdActividad() {
    	String titulo = this.getTitulo();
    	String loginProfesor = getLoginProfesorCreador();
    	
    	String id = titulo + " - " + loginProfesor;
    	
    	return id;
    }
}

	

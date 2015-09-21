package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.RightNowClient;
import model.ContactDTO;
import model.VehiculoDTO;
import rn.service.IServiceRightNow;
import rn.service.impl.IServiceRightNowImpl;

/**
 * Servlet implementation class ServletForm
 */
@WebServlet("/ServletForm")
public class ServletForm extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
			servicio(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

			servicio(request, response);
	}
	
	
	

	private void servicio(HttpServletRequest request, HttpServletResponse response)
	{
				
		inyeccionDatosContact(request);
		request.setAttribute("mensaje", "Datos enviado de manera exitosa!! :)");
		irJSPI(request, response, "form.jsp");

	}
			
	
	
	private void inyeccionDatosContact(HttpServletRequest request)
	{
		IServiceRightNow iServiceRightNow = new IServiceRightNowImpl();
		
		long id = iServiceRightNow.createContact(request.getParameter("txtNombre"), 
				                       request.getParameter("txtApellidos"), 
				                       request.getParameter("txtFonoFijo"), 
				                       request.getParameter("txtFonoMovil"), 
				                       Integer.parseInt(request.getParameter("cboTipoDocumento")), 
				                       request.getParameter("txtNumeroDocumento"),
				                       request.getParameter("txtEmail"));
		System.out.println("Nuevo Contacto credo: "+id);
	}
	
	
	
	private void irJSPI(HttpServletRequest _request, HttpServletResponse _response, String _jsp)
	{
		try
		{
			_request.getRequestDispatcher(_jsp).forward(_request, _response);
		} 
		catch (ServletException | IOException e)
		{
			e.printStackTrace();
		}
	}


}

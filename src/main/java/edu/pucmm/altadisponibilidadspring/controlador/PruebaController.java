package edu.pucmm.altadisponibilidadspring.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PruebaController {


    /**
     * Vista index del proyecto, podemos ver el contador del proyecto.
     * @param model
     * @param sesion
     * @param request
     * @return
     */
    @RequestMapping("/")
    public String index(Model model, HttpSession sesion, HttpServletRequest request){
        System.out.println("Entrando al controlador....");
        //
        Integer contador = (Integer) sesion.getAttribute("contador");
        if(contador == null){
            contador = 1;
        }
        contador++;
        sesion.setAttribute("contador", contador);
        //
        model.addAttribute("contador", contador);
        model.addAttribute("puerto", ""+request.getLocalPort());


        return "index";
    }

    /**
     * Ruta segura, cuando utilicemos el balanceador de carga la sesion
     * debe compartirse con entre los diferentes proyectos.
     * @param model
     * @param sesion
     * @param request
     * @return
     */
    @RequestMapping("/admin/")
    public String zonaAdmin(Model model, HttpSession sesion, HttpServletRequest request){
        System.out.println("Entrando a la zona admin....");
        model.addAttribute("servidor", ""+request.getLocalAddr());
        model.addAttribute("puerto", ""+request.getLocalPort());
        return "/admin/index.html";
    }

    /**
     *
     * @param model
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session, RedirectAttributes redirectAttributes){
        //cerrando la sesion.
        session.invalidate();
        return "redirect:/";
    }
}

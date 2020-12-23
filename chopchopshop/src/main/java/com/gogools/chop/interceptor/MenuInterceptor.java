package com.gogools.chop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gogools.enums.Emj;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MenuInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object o, ModelAndView model) throws Exception {

		if (model != null && !isRedirectView(model)) {
			
			addMenu(model);
		}
	}

	private void addMenu(ModelAndView model) {
		
		log.info(Emj.INFO + " addMenu =========================");

		model.addObject("menuInterceptorString", "menuInterceptorString");

		log.info(Emj.INFO + " addMenu =========================");
	}

	public static boolean isRedirectView(ModelAndView mv) {
		
		String viewName = mv.getViewName();
		if (viewName.startsWith("redirect:/")) {
			
			return true;
		}
		
		View view = mv.getView();
		return (view != null && view instanceof SmartView && ((SmartView) view).isRedirectView());
	}
}

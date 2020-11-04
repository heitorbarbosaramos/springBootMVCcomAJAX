package com.heitor.springBootComAjax.dwr;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.heitor.springBootComAjax.repository.PromocaoRepository;

@Component
@RemoteProxy
public class DWRAlertaPromocoes {

	@Autowired
	private PromocaoRepository repoPromo;
	private Timer timer;
	
	@RemoteMethod
	public synchronized void init() {
		System.out.println("DWR está ativado");
		LocalDateTime lastDate = getDtCadastroUltimaPromocao();
		WebContext context = WebContextFactory.get();
		timer = new Timer();
		timer.schedule(new AlertTask(context, lastDate), 10000, 60000);
	}
	
	private LocalDateTime getDtCadastroUltimaPromocao() {
		PageRequest pageRequest = PageRequest.of(0, 1, Direction.DESC, "dataCadastro");
		return repoPromo.findUltimaDataPromocao(pageRequest).getContent().get(0);
	}
	
	class AlertTask extends TimerTask{
		
		private LocalDateTime lastDate;
		private WebContext context;
		private Long count;
		
		
		
		public AlertTask(WebContext context, LocalDateTime lastDate) {
			super();
			this.lastDate = lastDate;
			this.context = context;
		}



		@Override
		public void run() {
			
			String session = context.getScriptSession().getId();
			
			Browser.withSession(context, session, new Runnable() {
				
				@Override
				public void run() {

					Map<String, Object> map = repoPromo.findUltimaPromcaoByDataCadastro(lastDate);
					
					count = (Long) map.get("count");
					lastDate = map.get("lastDate") == null ? lastDate : (LocalDateTime)  map.get("lastDate");
					
					Calendar time =  Calendar.getInstance();
					time.setTimeInMillis(context.getScriptSession().getLastAccessedTime());
					
					System.out.println("Count: " + count 
							+ ", lastDate: " + lastDate
							+ "<" + session + ">"
							+ "<" + time.getTime() + ">");
					
					if(count > 0) {
						ScriptSessions.addFunctionCall("showButton", count);
					}
					
				}
			});
			
		}
	}
}

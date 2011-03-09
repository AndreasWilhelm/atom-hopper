package net.jps.atom.hopper.jetty;

import com.rackspace.cloud.commons.util.servlet.context.impl.ServletSpringContext;
import net.jps.atom.hopper.AtomHopperServlet;
import net.jps.atom.hopper.servlet.ServletInitParameter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;

/**
 *
 * 
 */
public class AtomHopperJettyServerBuilder {

    private final int portNumber;

    public AtomHopperJettyServerBuilder(int portNumber) throws Exception {
        this.portNumber = portNumber;
    }

    private Server buildNewInstance() {
        final Server jettyServerReference = new Server(portNumber);
        final ServletContextHandler rootContext = buildRootContext(jettyServerReference);

        final ServletHolder atomHopServer = new ServletHolder(AtomHopperServlet.class);
        atomHopServer.setInitParameter(ServletInitParameter.CONTEXT_ADAPTER_CLASS.toString(), ServletSpringContext.class.getName());
        atomHopServer.setInitParameter(ServletInitParameter.CONFIGURATION_LOCATION.toString(), "classpath:/META-INF/atom-server.cfg.xml");

        rootContext.addServlet(atomHopServer, "/*");

        return jettyServerReference;
    }

    private ServletContextHandler buildRootContext(Server serverReference) {
        final ServletContextHandler servletContext = new ServletContextHandler(serverReference, "/");
        servletContext.getInitParams().put("contextConfigLocation", "classpath:/META-INF/application-context.xml");
        servletContext.addEventListener(new ContextLoaderListener());

        return servletContext;
    }

    public Server newServer() {
        return buildNewInstance();
    }

    public static void main(String[] args) throws Exception {
        new AtomHopperJettyServerBuilder(8080).newServer().start();
    }
}

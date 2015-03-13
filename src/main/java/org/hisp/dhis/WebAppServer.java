/*
 * Copyright (c) 2004-2009, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the HISP project nor the names of its contributors may
 *   be used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/**
 *
 * @author Bob Jolliffe
 * @version $$Id$$
 */
package org.hisp.dhis;

import java.io.File;
import java.net.URI;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author Bob Jolliffe
 */
public class WebAppServer extends Thread {

    public static final String DHIS_DIR = "/webapps/dhis";

    public static final String JETTY_PORT_CONF = "/conf/jetty.port";

    public static final int DEFAULT_JETTY_PORT = 8080;

    public static int MAX_FORM_CONTENT_SIZE = 5000000;

    private static final Log log = LogFactory.getLog(WebAppServer.class);

    protected Server server;

    public WebAppServer(String installDir, LifeCycle.Listener serverListener)
            throws Exception {
        
        int port;
        try (Scanner scanner = new Scanner(new File(installDir + JETTY_PORT_CONF))) {
            port = scanner.nextInt();
            log.info("Loading DHIS 2 on port: " + port);
        } catch (Exception ex) {
            log.info("Couldn't load port number from " + installDir + JETTY_PORT_CONF);
            port = DEFAULT_JETTY_PORT;
            log.info("Loading DHIS 2 on port: " + DEFAULT_JETTY_PORT);
        }

        server = new Server(port);
        server.addLifeCycleListener(serverListener);
        loadDHISContext(installDir + DHIS_DIR);
    }

    private void loadDHISContext(String webappPath) {
        WebAppContext dhisWebApp = new WebAppContext();
        dhisWebApp.setMaxFormContentSize(MAX_FORM_CONTENT_SIZE);
        dhisWebApp.setWar(webappPath);
        log.info("Setting DHIS 2 web app context to: " + webappPath);

        server.setHandler(dhisWebApp);
    }

    @Override
    public void run() {
        try {
            log.debug("Server thread starting");
            server.start();
            log.debug("Server thread exiting");
        } catch (Exception ex) {
            log.error("Server wouldn't start : " + ex);
        }
    }

    public void shutdown()
            throws Exception {
        server.stop();
    }

    public URI getURI() {
        return server.getURI();
    }
}

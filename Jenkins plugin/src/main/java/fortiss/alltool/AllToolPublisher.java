package fortiss.alltool;

import hudson.Launcher;
import hudson.Extension;
import hudson.model.Action;
import hudson.tasks.*;
import hudson.util.FormValidation;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Class to publish build result
 * @author: Md Shifuddin Al Masud(shifuddin.gmail.com)
 * */
public class AllToolPublisher extends Recorder {

    private final String source = "";
    private final String workflow = "";
    private final String configuration = "";

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public AllToolPublisher() {
    }

    //
     // We'll use this from the <tt>config.jelly</tt>.
     //
    public String getSrc() {
        return source;
    }
    public String getWrkflow() {
        return workflow;
    }
	public String getConf() {
        return configuration;
    }
    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        
        // This is where you 'build' the project.
        AllToolBuildAction buildAction = new AllToolBuildAction(build, build.getWorkspace().toString());
        build.addAction(buildAction);

        return true;
    }

    // Overridden for better type safety.
    // If your plugin doesn't really define any property on Descriptor,
    // you don't have to do this.
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

   
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {
        /**
         * In order to load the persisted global configuration, you have to
         * call load() in the constructor.
         */
        public DescriptorImpl() {
            load();
        }

       
        public FormValidation doCheckName(@QueryParameter String value)
                throws IOException, ServletException {
					
					try{
						
						int numLine = Integer.parseInt(value);
						return FormValidation.error("Please set Engine path");
						
					}
					catch (Exception  e)
					{

						
						return FormValidation.ok();
					}		
        }

        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types 
            return true;
        }

        /**
         * This human readable name is used in the configuration screen.
         */
        public String getDisplayName() {
            return "Publish autoanalyse result";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            // To persist global configuration information,
            // set that to properties and call save().
            // ^Can also use req.bindJSON(this, formData);
            //  (easier when there are many fields; need set* methods for this, like setUseFrench)
            save();
            return super.configure(req, formData);
        }
    }
}


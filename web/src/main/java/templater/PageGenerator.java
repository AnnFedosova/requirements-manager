package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class PageGenerator {
    private static final String HTML_DIR = "html/";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public static PageGenerator getInstance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + filename);
            if (data == null) {
                template.process(new HashMap<String, Object>(), stream);
            }
            else {
                template.process(data, stream);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator() {
        cfg = new Configuration(new Version(2, 3, 28));
        cfg.setClassForTemplateLoading(this.getClass(), "/");
    }
}
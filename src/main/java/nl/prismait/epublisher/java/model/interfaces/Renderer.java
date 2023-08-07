package nl.prismait.epublisher.java.model.interfaces;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

//import nl.prismait.epublisher.java.model.ArticleWrapper;
import nl.prismait.epublisher.java.model.ETemplate;
import nl.prismait.epublisher.java.model.Edition;

public interface Renderer {
	public String render(File template, Edition edition) throws RenderingException;
	public void renderToWriter(Writer writer, File template, Edition edition) throws RenderingException;

	public String render(String templateName, Edition edition, ETemplate eTemplate) throws IOException, RenderingException;
//	public String renderArticle(Edition edition, ETemplate eTemplate, ArticleWrapper articleWrapper) throws IOException, RenderingException;

	StringWriter renderToWriter(String templateName, Edition edition, ETemplate eTemplate) throws IOException;
//	StringWriter renderArticleToWriter(Edition edition, ETemplate eTemplate, ArticleWrapper articleWrapper) throws IOException;
}

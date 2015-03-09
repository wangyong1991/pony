package pony.http;

public enum Mime {
	/**
     * Common mime type for dynamic content: html
     */
	MIME_HTML("text/html"),
	 /**
     * Common mime type for dynamic content: plain text
     */
	MIME_PLAINTEXT("text/plain");
	
	private String content;
	
	Mime(final String content){
		this.content = content;
	}

	public final String getContent() {
		return content;
	}
	
	
}

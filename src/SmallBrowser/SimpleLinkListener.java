package SmallBrowser;

import java.io.FileNotFoundException;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

public class SimpleLinkListener implements HyperlinkListener {

	private JEditorPane pane; // The pane we're using to display HTML

	private JTextField urlField; // An optional text field for showing
									// the current URL being displayed

	private JLabel statusBar; // An optional label for showing where
								// a link would take you

	public SimpleLinkListener(JEditorPane jep, JTextField jtf, JLabel jl) {
		pane = jep;
		urlField = jtf;
		statusBar = jl;
	}

	public SimpleLinkListener(JEditorPane jep) {
		this(jep, null, null);
	}

	public void hyperlinkUpdate(HyperlinkEvent he) {
		HyperlinkEvent.EventType type = he.getEventType();
		if (type == HyperlinkEvent.EventType.ENTERED) {
			// Enter event. Fill in the status bar.
			if (statusBar != null) {
				statusBar.setText(he.getURL().toString());
			}
		} else if (type == HyperlinkEvent.EventType.EXITED) {
			// Exit event. Clear the status bar.
			if (statusBar != null) {
				statusBar.setText(" "); // Must be a space or it disappears
			}
		} else if (type == HyperlinkEvent.EventType.ACTIVATED) {
			// Jump event. Get the URL, and, if it's not null, switch to that
			// page in the main editor pane and update the "site url" label.
			if (he instanceof HTMLFrameHyperlinkEvent) {
				// Ahh, frame event; handle this separately.
				HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) he;
				HTMLDocument doc = (HTMLDocument) pane.getDocument();
				doc.processHTMLFrameHyperlinkEvent(evt);
			} else {
				try {
					pane.setPage(he.getURL());
					if (urlField != null) {
						urlField.setText(he.getURL().toString());
					}
				} catch (FileNotFoundException fnfe) {
					pane.setText("Could not open file: <tt>" + he.getURL() + "</tt>.<hr>");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
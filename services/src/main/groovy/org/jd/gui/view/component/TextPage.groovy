/*
 * Copyright (c) 2008-2015 Emmanuel Dupuy
 * This program is made available under the terms of the GPLv3 License.
 */

package org.jd.gui.view.component

import groovy.transform.CompileStatic
import org.jd.gui.api.API
import org.jd.gui.api.feature.ContentCopyable
import org.jd.gui.api.feature.ContentSavable
import org.jd.gui.api.feature.ContentSelectable
import org.jd.gui.util.io.NewlineOutputStream

import java.awt.datatransfer.StringSelection

@CompileStatic
class TextPage extends AbstractTextPage implements ContentCopyable, ContentSelectable, ContentSavable {

    // --- ContentCopyable --- //
    void copy() {
        if (textArea.selectionStart == textArea.selectionEnd) {
            toolkit.systemClipboard.setContents(new StringSelection(''), null)
        } else {
            textArea.copyAsRtf()
        }
    }

    // --- ContentSelectable --- //
    void selectAll() {
        textArea.selectAll()
    }

    // --- ContentSavable --- //
    String getFileName() { 'file.txt' }

    void save(API api, OutputStream os) {
        new NewlineOutputStream(os).withWriter('UTF-8') { Writer w ->
            w.write(textArea.text)
        }
    }
}

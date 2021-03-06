/*
 * Copyright (c) 2001-2017, Zoltan Farkas All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Additionally licensed with:
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spf4j.ui;
//CHECKSTYLE:OFF

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.event.ItemEvent;
import org.spf4j.stackmonitor.SampleNode;

/**
 * will need to add some standard filtering:
 *
 * Pair.of(sun.misc.Unsafe.class.getName(), "park")); Pair.of(java.lang.Object.class.getName(), "wait"));
 * Pair.of(java.lang.Thread.class.getName(), "sleep")); Pair.of("java.net.PlainSocketImpl", "socketAccept"));
 * Pair.of("java.net.PlainSocketImpl", "socketConnect"));
 *
 * @author zoly
 */
@SuppressFBWarnings({"FCBL_FIELD_COULD_BE_LOCAL", "SE_BAD_FIELD"})
public class StackDumpJInternalFrame extends javax.swing.JInternalFrame {

  private SampleNode samples;

  /**
   * Creates new form StackDumpJInternalFrame
   */
  public StackDumpJInternalFrame(final SampleNode samples,
          final String title, final boolean isgraph) {
    super(title);
    setName(title);
    initComponents();
    if (samples == null) {
      this.samples = new SampleNode(new StackTraceElement[]{new StackTraceElement("NO SAMPLES", "", "", -1)}, 0);
    } else {
      this.samples = samples;
    }
    setViewType(isgraph);
    ssScrollPanel.setVisible(true);
    pack();
  }

  private void setViewType(final boolean isgraph) {
    if (isgraph) {
      graphToggle.setSelected(true);
      //ssScrollPanel.setViewportView(new ZStackPanel(this.samples));
      ssScrollPanel.setViewportView(new HotFlameStackPanel(this.samples));
    } else {
      graphToggle.setSelected(false);
      ssScrollPanel.setViewportView(new FlameStackPanel(this.samples));
    }
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
   * content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  @SuppressFBWarnings
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    ssScrollPanel = new javax.swing.JScrollPane();
    jToolBar1 = new javax.swing.JToolBar();
    graphToggle = new javax.swing.JToggleButton();

    setClosable(true);
    setIconifiable(true);
    setMaximizable(true);
    setResizable(true);

    ssScrollPanel.setAutoscrolls(true);

    jToolBar1.setRollover(true);

    graphToggle.setText("graph");
    graphToggle.setFocusable(false);
    graphToggle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    graphToggle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    graphToggle.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        graphToggleItemStateChanged(evt);
      }
    });
    jToolBar1.add(graphToggle);

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(ssScrollPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
      .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
        .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(ssScrollPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void graphToggleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_graphToggleItemStateChanged
    setViewType(evt.getStateChange() == ItemEvent.SELECTED);
  }//GEN-LAST:event_graphToggleItemStateChanged

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JToggleButton graphToggle;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JScrollPane ssScrollPanel;
  // End of variables declaration//GEN-END:variables
}

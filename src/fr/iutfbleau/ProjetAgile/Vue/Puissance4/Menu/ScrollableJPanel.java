package fr.iutfbleau.ProjetAgile.Vue.Puissance4.Menu;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import java.awt.*;

class ScrollableJPanel extends JPanel implements Scrollable {

    @Override
    public Dimension getPreferredScrollableViewportSize() {
      // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
      // TODO Auto-generated method stub
      return 10;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
      // TODO Auto-generated method stub
      return 10;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
      // TODO Auto-generated method stub
      return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
      // TODO Auto-generated method stub
      return false;
    }}


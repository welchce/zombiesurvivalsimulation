/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;

import java.io.Serializable;
import java.awt.Point;

/**
 *
 * @author ryancummins
 */
public class ActionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private ActionEnum _action;
    private Point _location;
    private Creature _creature;

    public ActionEntity(Creature creature, ActionEnum action, Point location) {
        _creature = creature;
        _action = action;
        _location = location;
    }
    
    public Point getActionLocation() {
        return _location;
    }
    
    public ActionEnum getAction() {
        return _action;
    }

    public Creature getCreature() {
        return _creature;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (_creature != null ? _creature.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionEntity)) {
            return false;
        }
        ActionEntity other = (ActionEntity) object;
        if ((this._creature == null && other._creature != null) || (this._creature != null && !this._creature.equals(other._creature))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + _creature + "]";
    }

}

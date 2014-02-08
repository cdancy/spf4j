/*
 * Copyright (c) 2001, Zoltan Farkas All Rights Reserved.
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
 */
package org.spf4j.zel.instr.var;

import java.util.Iterator;
import java.util.List;
import org.spf4j.zel.instr.Instruction;
import org.spf4j.zel.vm.EndParamMarker;
import org.spf4j.zel.vm.ExecutionContext;
import org.spf4j.zel.vm.SuspendedException;
import org.spf4j.zel.vm.ZExecutionException;



public final class MAX extends Instruction {

    private static final long serialVersionUID = -7339415434652687086L;

    private MAX() {
    }

    @Override
    public void execute(final ExecutionContext context)
            throws ZExecutionException, SuspendedException {
        List<Object> params = context.popSyncStackValsUntil(EndParamMarker.INSTANCE);
        Iterator<Object> it = params.iterator();
        Comparable max = (Comparable) it.next();
        while (it.hasNext()) {
            Comparable obj = (Comparable) it.next();
            if (max.compareTo(obj) < 0) {
                max = obj;
            }
        }
        context.push(max);
        context.ip++;
    }

    /**
     * instance
     */
    public static final Instruction INSTANCE = new MAX();

}

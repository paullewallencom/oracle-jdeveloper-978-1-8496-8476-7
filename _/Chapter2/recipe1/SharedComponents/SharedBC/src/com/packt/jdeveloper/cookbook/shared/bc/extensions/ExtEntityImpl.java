package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.jbo.AttributeDef;
import oracle.jbo.AttributeList;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;

public class ExtEntityImpl extends EntityImpl {
    static final String CREATESEQ_PROPERTY = "CreateSequence";

    /**
     * Populates entity attributes from a database sequence defined generically using a
     * CREATESEQ_PROPERTY custom property.
     *
     * @param attributeList
     */
    @Override
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        // iterate all entity attributes
        for (AttributeDef atrbDef : this.getEntityDef().getAttributeDefs()) {
            // check for a custom property called CREATESEQ_PROPERTY
            String sequenceName =
                (String)atrbDef.getProperty(CREATESEQ_PROPERTY);
            if (sequenceName != null) {
                // create the sequence based on the custom property sequence name
                SequenceImpl sequence =
                    new SequenceImpl(sequenceName, this.getDBTransaction());
                // populate the attribute with the next sequence number
                this.populateAttributeAsChanged(atrbDef.getIndex(),
                                                sequence.getSequenceNumber());
            }
        }
    }
}

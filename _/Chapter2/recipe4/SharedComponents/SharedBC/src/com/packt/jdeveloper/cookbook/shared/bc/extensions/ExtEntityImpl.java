package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.jbo.AttributeDef;
import oracle.jbo.AttributeList;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;

public class ExtEntityImpl extends EntityImpl {
    static final String CREATESEQ_PROPERTY = "CreateSequence";
    static final String COMMITSEQ_PROPERTY = "CommitSequence";

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
            // construct the custom property name from the entity name and attribute
            String propertyName =
                CREATESEQ_PROPERTY + getEntityDef().getName() +
                atrbDef.getName();
            // check for a custom property called CREATESEQ_PROPERTY
            String sequenceName = (String)atrbDef.getProperty(propertyName);
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

    @Override
    protected void doDML(int operation, TransactionEvent transactionEvent) {

        // check for insert operation
        if (DML_INSERT == operation) {
            // iterate all entity attributes
            for (AttributeDef atrbDef :
                 this.getEntityDef().getAttributeDefs()) {
                // construct the custom property name from the entity name and attribute
                String propertyName =
                    COMMITSEQ_PROPERTY + getEntityDef().getName() +
                    atrbDef.getName();
                // check for a custom property called COMMITSEQ_PROPERTY
                String sequenceName =
                    (String)atrbDef.getProperty(propertyName);
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

        super.doDML(operation, transactionEvent);
    }

    /**
     * Check if attribute's value differs from its posted value
     * @param attrIdx the attribute index
     * @return
     */
    protected boolean isAttrValueChanged(int attrIdx) {
        // get the attribute's posted value
        Object postedValue = getPostedAttribute(attrIdx);
        // get the attribute's current value
        Object newValue = getAttributeInternal(attrIdx);
        // return true is attribute value differs from its posted value
        return isAttributeChanged(attrIdx) &&
            ((postedValue == null && newValue != null) ||
             (postedValue != null && newValue == null) ||
             (postedValue != null && newValue != null &&
              !newValue.equals(postedValue)));
    }

}

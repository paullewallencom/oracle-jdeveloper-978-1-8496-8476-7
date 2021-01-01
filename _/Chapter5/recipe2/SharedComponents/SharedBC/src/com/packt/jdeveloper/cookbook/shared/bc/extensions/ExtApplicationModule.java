package com.packt.jdeveloper.cookbook.shared.bc.extensions;

/**
 * Recipe: Creating and using generic extension interfaces.
 */
public interface ExtApplicationModule {
    // return some user authority level, based on the user's name    
    public int getUserAuthorityLevel();
}

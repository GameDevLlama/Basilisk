package com.llama.basilisk.sample.model;

import com.llama.basilisk.Model;
import com.llama.basilisk.binding.Bindable;

/**
 * Created by Christian Ringshofer on 01.09.15.
 */
@Model
public interface UserModel {

    Bindable<String> username();

    Bindable<String> password();

}

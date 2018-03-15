package com.usermind.usermindsdk.common.crypto;

import com.usermind.usermindsdk.common.config.Configuration;


public interface ConfigurationCrypto {

  Configuration encrypt(Configuration original);

  Configuration decrypt(Configuration encrypted);
}

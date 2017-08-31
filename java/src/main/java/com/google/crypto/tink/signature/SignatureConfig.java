// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
////////////////////////////////////////////////////////////////////////////////

package com.google.crypto.tink.signature;

import com.google.crypto.tink.Config;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

/**
 * Static methods and constants for registering with the {@link Registry} all instances of
 * {@link com.google.crypto.tink.PublicKeySign} and {@link com.google.crypto.tink.PublicKeyVerify}
 * key types supported in a particular release of Tink.
 *
 * <p>To register all PublicKeySign and PublicKeyVerify key types provided in Tink release
 * 1.0.0 one can do:
 * <pre>{@code
 * Config.register(HybridConfig.TINK_1_0_0);
 * }</pre>
 *
 * <p>For more information on how to obtain and use instances of PublicKeySign or PublicKeyVerify,
 * see {@link PublicKeySignFactory} or {@link PublicKeyVerifyFactory}.
 */
public final class SignatureConfig {
  private static final String CATALOGUE_NAME = "TinkSignature";

  public static final RegistryConfig TINK_1_0_0 = RegistryConfig.newBuilder()
        .setConfigName("TINK_SIGNATURE_SIGN_1_0_0")
        .addEntry(Config.getTinkKeyTypeEntry(
            CATALOGUE_NAME, "PublicKeySign", "EcdsaPrivateKey", 0, true))
        .addEntry(Config.getTinkKeyTypeEntry(
            CATALOGUE_NAME, "PublicKeySign", "Ed25519PrivateKey", 0, true))
        .addEntry(Config.getTinkKeyTypeEntry(
            CATALOGUE_NAME, "PublicKeyVerify", "EcdsaPublicKey", 0, true))
        .addEntry(Config.getTinkKeyTypeEntry(
            CATALOGUE_NAME, "PublicKeyVerify", "Ed25519PublicKey", 0, true))
        .build();

  static {
    try {
      init();
    } catch (GeneralSecurityException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  /**
   * Tries to register with the {@link Registry} all instances of
   * {@link com.google.crypto.tink.Catalogue} needed to handle PublicKeySign and PublicKeyVerify
   * key types supported in Tink.
   */
  public static void init() throws GeneralSecurityException {
    Registry.addCatalogue(CATALOGUE_NAME, new SignatureCatalogue());
  }
}

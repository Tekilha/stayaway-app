/*
 * Copyright (c) 2020 INESC TEC <https://www.inesctec.pt>
 *
 * This Source Code Form is subject to the terms of the European Union
 * Public License, v. 1.2. If a copy of the EUPL was not distributed with
 * this file, You can obtain one at https://opensource.org/licenses/EUPL-1.2.
 *
 * SPDX-License-Identifier: EUPL-1.2
 */

/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package fct.inesctec.stayaway.tracing.internal.networking.models;

public class ParametersModel {

    private int lowerThreshold;
    private int higherThreshold;
    private float factorLow;
    private float factorHigh;
    private int triggerThreshold;

    public int getLowerThreshold() {
        return lowerThreshold;
    }

    public int getHigherThreshold() {
        return higherThreshold;
    }

    public float getFactorLow() {
        return factorLow;
    }

    public float getFactorHigh() {
        return factorHigh;
    }

    public int getTriggerThreshold() {
        return triggerThreshold;
    }

}

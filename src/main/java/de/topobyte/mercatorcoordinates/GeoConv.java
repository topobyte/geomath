// Copyright 2021 Sebastian Kuerten
//
// This file is part of geomath.
//
// geomath is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// geomath is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with geomath. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.mercatorcoordinates;

import de.topobyte.geomath.WGS84;

/**
 * This class provides methods for calculations with the WGS84 projection that
 * are useful for working with Mercator coordinates that are stored as pairs of
 * int values.
 * 
 * @author Sebastian Kuerten (sebastian@topobyte.de)
 */
public class GeoConv
{

	public static int MERCATOR_SHIFT = 26;
	public static int MERCATOR_SIZE = 1 << MERCATOR_SHIFT;

	public static int mercatorFromLongitude(double lon)
	{
		double mx = WGS84.lon2merc(lon, MERCATOR_SIZE);
		return (int) Math.round(mx);
	}

	public static int mercatorFromLatitude(double lat)
	{
		double my = WGS84.lat2merc(lat, MERCATOR_SIZE);
		return (int) Math.round(my);
	}

	public static double mercatorToLongitude(double mx)
	{
		return WGS84.merc2lon(mx, MERCATOR_SIZE);
	}

	public static double mercatorToLatitude(double my)
	{
		return WGS84.merc2lat(my, MERCATOR_SIZE);
	}

	/**
	 * Convert from storage mercator to target zoom mercator.
	 */
	public static int getX(int mx, int targetZoom)
	{
		int shift = MERCATOR_SHIFT - targetZoom - 8;
		boolean inverse = false;
		if (shift < 0) {
			shift *= -1;
			inverse = true;
		}
		int smx = inverse ? mx << shift : mx >> shift;
		return smx;
	}

	/**
	 * Convert from storage mercator to target zoom mercator.
	 */
	public static int getY(int my, int targetZoom)
	{
		int shift = MERCATOR_SHIFT - targetZoom - 8;
		boolean inverse = false;
		if (shift < 0) {
			shift *= -1;
			inverse = true;
		}
		int smy = inverse ? my << shift : my >> shift;
		return smy;
	}

}

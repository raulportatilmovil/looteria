// hooks/useFetch.js
import { useState, useEffect } from 'react';
import axiosInstance from '../api/axiosConfig';

/**
 * Custom hook para hacer fetch de datos con manejo de estados
 * @param {string} url - URL relativa del endpoint (sin baseURL)
 * @param {object} options - Opciones adicionales (method, data, dependencies, etc)
 */
export const useFetch = (url, options = {}) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const {
    method = 'GET',
    body = null,
    dependencies = [],
    skip = false,
  } = options;

  useEffect(() => {
    if (skip) return;

    const fetchData = async () => {
      try {
        setLoading(true);
        const config = {
          method,
          url,
        };

        if (body && (method === 'POST' || method === 'PUT' || method === 'PATCH')) {
          config.data = body;
        }

        const response = await axiosInstance(config);
        setData(response.data);
        setError(null);
      } catch (err) {
        setError(err.response?.data?.message || err.message);
        setData(null);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [url, method, body, skip, ...dependencies]);

  return { data, loading, error };
};

/**
 * Custom hook para mutaciones (POST, PUT, DELETE)
 */
export const useMutation = (url, options = {}) => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [data, setData] = useState(null);

  const { method = 'POST' } = options;

  const mutate = async (payload) => {
    try {
      setLoading(true);
      setError(null);

      const response = await axiosInstance({
        method,
        url,
        data: payload,
      });

      setData(response.data);
      return response.data;
    } catch (err) {
      const errorMessage = err.response?.data?.message || err.message;
      setError(errorMessage);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  return { mutate, loading, error, data };
};

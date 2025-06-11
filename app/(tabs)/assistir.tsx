import React, { useState, useEffect } from 'react';
import { useLocalSearchParams, router } from 'expo-router';
import { StyleSheet, View, Text, TouchableOpacity, ActivityIndicator, Alert } from 'react-native';
import { Video, ResizeMode } from 'expo-av';
import AsyncStorage from '@react-native-async-storage/async-storage';
import api from '../api/api'; 

export default function Assistir() {
  const { id } = useLocalSearchParams<{ id: string }>();
  const [videoUrl, setVideoUrl] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);
  const [errorMsg, setErrorMsg] = useState<string | null>(null);

  useEffect(() => {
    // Função para buscar metadados do filme
    const fetchFilme = async () => {
      if (!id) {
        setErrorMsg('ID inválido');
        setLoading(false);
        return;
      }
      try {
        // Se precisar enviar token:
        const token = await AsyncStorage.getItem('token');
        const headers = token ? { Authorization: `Bearer ${token}` } : {};
        // Chama GET /filmes/{id}
        const response = await api.get(`/filmes/${id}`, { headers });
        const data = response.data;
        // Espera que data.videoUrl exista:
        if (data && data.videoUrl) {
          setVideoUrl(data.videoUrl);
        } else {
          setErrorMsg(`URL de vídeo não disponível para o filme ${id}`);
        }
      } catch (error: any) {
        console.error('Erro ao buscar metadados do filme:', error);
        if (error.response) {
          const dataErro = error.response.data;
          const msg = dataErro?.mensagem || JSON.stringify(dataErro);
          setErrorMsg(msg);
        } else {
          setErrorMsg('Não foi possível conectar ao servidor.');
        }
      } finally {
        setLoading(false);
      }
    };
    fetchFilme();
  }, [id]);

  if (loading) {
    return (
      <View style={styles.container}>
        <ActivityIndicator size="large" color="#fff" />
      </View>
    );
  }

  if (errorMsg) {
    return (
      <View style={styles.container}>
        <Text style={styles.textError}>{errorMsg}</Text>
        <TouchableOpacity onPress={() => router.push('/casa')}>
          <Text style={styles.link}>VOLTAR</Text>
        </TouchableOpacity>
      </View>
    );
  }

  if (!videoUrl) {
    return (
      <View style={styles.container}>
        <Text style={styles.textError}>Vídeo não disponível para o id: {id}</Text>
        <TouchableOpacity onPress={() => router.push('/casa')}>
          <Text style={styles.link}>VOLTAR</Text>
        </TouchableOpacity>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Video
        source={{ uri: videoUrl }}
        useNativeControls
        resizeMode={ResizeMode.CONTAIN}
        shouldPlay
        style={styles.video}
        // Você pode ajustar props: isLooping, onError, onPlaybackStatusUpdate, etc.
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
  },
  video: {
    width: '100%',
    height: 300,
    backgroundColor: '#000',
  },
  link: {
    marginTop: 20,
    textAlign: 'center',
    color: '#fff',
    textDecorationLine: 'underline',
    fontSize: 20,
  },
  textError: {
    fontSize: 18,
    color: '#fff',
    textAlign: 'center',
    marginHorizontal: 16,
  },
});

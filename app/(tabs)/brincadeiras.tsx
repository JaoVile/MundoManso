import React from 'react';
import { Image, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { router } from 'expo-router';

export default function Brincadeiras() {
  return (
    
    <View style={styles.container}>
      <Text style={styles.title}>Área de Brincadeiras/ não disponível.</Text>

      <TouchableOpacity onPress={() => router.push('/')}>
             <Text style={styles.link}>VOLTAR</Text>
            </TouchableOpacity>
    </View>
  );
  
}

const styles = StyleSheet.create({
  container: { flex: 1, alignItems: 'center',justifyContent: "center"},
  title: { fontSize: 20, fontWeight: 'bold' },

link: {
    marginTop: 20,
    textAlign: 'center',
    color: 'black',
    textDecorationLine: 'underline',
    fontSize: 20,
  },
});
